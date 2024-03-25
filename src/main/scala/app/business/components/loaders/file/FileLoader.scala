package app.business.components.loaders.file

import app.business.components.loaders.Loader

trait FileLoader[B] extends Loader[String, B]
