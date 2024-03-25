package app.business.pipeline.stages

import app.business.pipeline.jobs.Job

import scala.collection.immutable.Seq
import scala.collection.mutable

/**
 * Class representing a stage where the same types of the jobs can repeat.
 */
abstract class SimpleStage[I, O] extends Stage[I, O] {

  private val _jobs: mutable.Buffer[Job[I, O]] = mutable.Buffer.empty

  override def jobs: Seq[Job[I, O]] = _jobs.toSeq

  override def addJob(job: Job[I, O]): Unit = _jobs += job

}
