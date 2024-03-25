package app.business.pipeline.jobs

import app.business.pipeline.Pipeline

/**
 * Class representing a generic job..
 * Jobs can be: filtering image, outputting ascii text, loading image, and so on.
 * Job could also exist and run inside a pipeline.
 *
 * @tparam I The type of the input that the jobs takes.
 * @tparam O The type of the output that the jobs produces.
 */
trait Job[I, O] {

  /**
   * Executes the jobs with the provided input.
   *
   * @param input The input of type T required to execute the jobs.
   * @return The output of the job.
   */
  def execute(input: I): O

  /**
   * The possible pipeline to which this job belongs.
   */
  def pipeline: Option[Pipeline]

}
