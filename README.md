<p align="center">
  <img src="https://github.com/kulikvl/ascii-art/assets/111417661/eaf7e07c-1e52-4eb6-b18a-0ebe2effd369" />
</p>

---

## Overview

The basic idea of this application is to load images, translate them into ASCII art, optionally apply filters, and output them (console, file).

## Architecture

The execution of translating an image to ASCII art is essentially running a pipeline (similar to one in GitLab). 
Thus, the pipeline is the core execution unit in the app and may contain various stages. 
Stages act as logical containers for jobs and include verification logic to determine what kinds of jobs can exist within a particular stage.
A job is the smallest unit of work within the pipeline. By specifying arguments (such as `--image path/to/image.png`), specific jobs are added. 
New jobs **can be implemented and integrated easily**, facilitating future development of the app. You can very easily add new filters, export/load image stategies, new algorithms of pixel to ascii conversion and so on.

The stages of application execution, **fixed by design**, are as follows:
- Loading Stage: This includes loading jobs that load images into an internal RGB pixel representation. It is triggered by commands like `--image "path/to/image.png"`.
- Conversion Stage: This stage consists of conversion jobs that transform the RGB image into an ASCII image based on a certain table. It is triggered by commands like `--custom-table "abc"`.
- Filter Stage: Includes filtering jobs that modify the ASCII image, for example, by inverting, scaling, or rotating it. This is triggered by commands such as `--rotate 90` or `--invert`.
- Export Stage: Comprises export jobs that send the resulting ASCII image to a destination, such as a file or standard output, triggered by commands like `--output-console`.

## Examples

```bash
sbt
run --image "samples/house.png" --output-file "house.txt"
run --image "samples/eliska.png" --output-file "eliska.txt" --scale 0.5 --invert
run --image "samples/face.png" --output-file "face.txt" --scale 2 --rotate 90
```

## Testing

The code is very well tested, unit tests cover every part of the code and total code coverage is close to 100%.

Run tests:
```bash
sbt run
```

