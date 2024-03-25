package app.business.pipeline.stages

import app.business.components.validators.ValidationResult
import app.business.pipeline.{Pipeline, SimplePipeline}
import app.business.pipeline.jobs.Job

/**
 * Class representing a logical container for jobs.
 *
 * Each concrete stage implementation should define the parameters on which the jobs are grouped,
 * and establish the criteria for what is considered valid to run the stage.
 *
 * @tparam I The type of the input that the stage takes.
 * @tparam O The type of the output that the stage produces.
 */
trait Stage[I, O] {

  /**
   * Returns the jobs the stage contains.
   */
  def jobs: Seq[Job[I, O]]

  /**
   * Adds the jobs to the stage.
   */
  def addJob(job: Job[I, O]): Unit

  /**
   * Runs the stage.
   *
   * Concrete stage implementation should define the order and jobs that would be ran.
   */
  def run(input: I): O

  /**
   * Verifies if the job setup of the stage is valid.
   *
   * For example the criteria for valid setup can be that the stage contains at least 2 different jobs.
   */
  def verifyJobSetup(): ValidationResult

}
