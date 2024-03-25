package app.business.pipeline

import app.business.components.validators.ValidationResult
import app.business.pipeline.jobs.Job
import app.business.pipeline.stages.Stage

import scala.collection.mutable

/**
 * Class representing a generic pipeline.
 *
 * Every pipeline may contain multiple stages, which are logical containers for jobs.
 * Concrete pipeline implementations should define these stages and provide methods for conveniently adding jobs to them.
 *
 * The pipeline can also be seen as a Facade, as it abstracts the process of running all jobs, including loading, filtering, and converting images.
 */
trait Pipeline {

  /**
   * Returns all the jobs the pipeline has.
   * Usually it is done by collecting the jobs from all the stages.
   */
  def jobs: Seq[Job[_, _]]

  /**
   * Adds the job to the pipeline.
   */
  def addJob[I, O] (job: Job[I, O]): Unit

  /**
   * Returns all the stages the pipeline has.
   */
  def stages: Seq[Stage[_, _]]

  /**
   * Control the correctness of the stages. It is a mandatory
   */
  def verifyJobSetup(): ValidationResult

  /**
   * Main orchestration process function.
   */
  def run(): Unit

  /**
   * Different jobs can produce or consume artifacts.
   * It is necessary for jobs that depends on the output of the previous jobs.
   */
  def artefacts: mutable.Map[String, Any]

}
