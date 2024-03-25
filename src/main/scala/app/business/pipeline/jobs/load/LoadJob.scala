package app.business.pipeline.jobs.load

import app.business.models.Image
import app.business.models.pixel.specific.RGBPixel
import app.business.pipeline.jobs.Job

/**
 * Class representing a job capable of loading RGB images.
 */
abstract class LoadJob extends Job[Unit, Image[RGBPixel]]
