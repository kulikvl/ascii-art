# ASCII Art
The idea of this project is to load images, translate them into ASCII ART images, optionally apply filters, and save them.

### Architecture & design of the app in a few words
The execution of translating an image to ASCII art is essentially running a pipeline (similar to one in GitLab). 
Thus, the pipeline is the core execution unit in the app and may contain various stages. 
Stages act as logical containers for jobs and include verification logic to determine what kinds of jobs can exist within a particular stage.
A job is the smallest unit of work within the pipeline. By specifying arguments (such as `--image path/to/image.png`), specific jobs are added. 
New jobs **can be implemented and integrated easily**, facilitating future development of the app.

The stages, **fixed by design**, are as follows:
- Loading Stage: This includes loading jobs that load images into an internal RGB pixel representation. It is triggered by commands like `--image "path/to/image.png"`.
- Conversion Stage: This stage consists of conversion jobs that transform the RGB image into an ASCII image based on a certain table. It is triggered by commands like `--custom-table "abc"`.
- Filter Stage: Includes filtering jobs that modify the ASCII image, for example, by inverting, scaling, or rotating it. This is triggered by commands such as `--rotate 90` or `--invert`.
- Export Stage: Comprises export jobs that send the resulting ASCII image to a destination, such as a file or standard output, triggered by commands like `--output-console`.

### Examples
```bash
run --image "src/main/resources/images/house.png" --output-file "src/main/resources/output/house.txt"
run --image "src/main/resources/images/eliska.png" --output-file "src/main/resources/output/eliska.txt" --scale 0.5 --invert
run --image "src/main/resources/images/face.png" --output-file "src/main/resources/output/face.txt" --scale 2 --rotate 90
```

### Tip
Set font-family: 'Courier New' in the browser to make ascii character font close to 1:1
