package app.business.pipeline

import app.business.components.validators.ValidationResult
import app.business.models.Image
import app.business.models.pixel.specific.{ASCIIPixel, RGBPixel}
import app.business.pipeline.jobs.Job
import app.business.pipeline.jobs.`export`.ExportJob
import app.business.pipeline.jobs.convert.ConvertJob
import app.business.pipeline.jobs.convert.specific.PredefinedTableConvertJob
import app.business.pipeline.jobs.filter.FilterJob
import app.business.pipeline.jobs.load.LoadJob
import app.business.pipeline.stages.Stage
import app.business.pipeline.stages.specific.{ConvertStage, ExportStage, FilterStage, LoadStage}

import scala.collection.mutable

class SimplePipeline() extends Pipeline {

  // Initialize the empty stages
  private val loadStage = new LoadStage
  private val convertStage = new ConvertStage
  private val filterStage = new FilterStage
  private val exportStage = new ExportStage

  override def stages: Seq[Stage[_, _]] = Seq(loadStage, convertStage, filterStage, exportStage)

  override def jobs: Seq[Job[_, _]] = stages.flatMap(_.jobs)

  private var jobsAdded: Int = 0

  override def addJob[I, O] (job: Job[I, O]): Unit = {
    jobsAdded += 1

    job match {
      case loadJob: LoadJob => loadStage.addJob(loadJob)
      case convertJob: ConvertJob => convertStage.addJob(convertJob)
      case filterJob: FilterJob => filterStage.addJob(filterJob)
      case exportJob: ExportJob => exportStage.addJob(exportJob)
      case _ => throw new IllegalArgumentException("Unknown job encountered")
    }
  }

  override def verifyJobSetup(): ValidationResult = {
    var hasErrors = false
    var msg = ""

    stages.foreach(s => {
      val valRes = s.verifyJobSetup()
      if (valRes.hasErrors) {
        hasErrors = true
        msg += s"${valRes.message}\n"
      }
    })

    ValidationResult(hasErrors, msg)
  }

  override def run(): Unit = {
    if (jobsAdded == 0) return

    // Verify job setup
    val valRes = verifyJobSetup()
    if (valRes.hasErrors) throw new RuntimeException(s"Pipeline verification failed: ${valRes.message}")

    // Run jobs (process facade)
    try {
      val loadStageOutput: Image[RGBPixel] = loadStage.run()
      val convertStageOutput: Image[ASCIIPixel] = convertStage.run(loadStageOutput)
      val filterStageOutput: Image[ASCIIPixel] = filterStage.run(convertStageOutput)
      exportStage.run(filterStageOutput)
    } catch {
      case e: Exception => throw new RuntimeException(s"Pipeline run failed: ${e.getMessage}")
    }

  }

  override val artefacts: mutable.Map[String, Any] = mutable.Map.empty

}
