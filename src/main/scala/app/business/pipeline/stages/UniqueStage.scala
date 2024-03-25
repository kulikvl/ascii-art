package app.business.pipeline.stages

import app.business.pipeline.jobs.Job
import utils.UniqueTypeBuffer

/**
 * Class representing a stage where the same types of the jobs can't repeat.
 */
abstract class UniqueStage[I, O] extends Stage[I, O] {

  private val _jobs: UniqueTypeBuffer[Job[I, O]] = new UniqueTypeBuffer

  override def jobs: Seq[Job[I, O]] = _jobs.toSeq

  override def addJob(job: Job[I, O]): Unit = _jobs.add(job)

}
