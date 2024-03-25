package app.business.pipeline.jobs.filter

import app.business.models.Image
import app.business.models.pixel.specific.ASCIIPixel
import app.business.pipeline.jobs.Job

/**
 * Class representing a job capable of filtering ASCII images.
 */
abstract class FilterJob extends Job[Image[ASCIIPixel], Image[ASCIIPixel]]
