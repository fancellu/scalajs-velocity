![scalajs-velocity Logo](http://felstar.com/projects/scalajs-velocity/img/scalajs-velocity-cliff.png)
# scalajs-velocity

## Scala.js bindings for velocity.js and example applications 

[![Build Status](https://travis-ci.org/fancellu/scalajs-velocity.svg?branch=master)](https://travis-ci.org/fancellu/scalajs-velocity)

Example apps showing the use of [Scala.js](http://www.scala-js.org/) with [Velocity.js](http://julian.com/research/velocity/) 
The bindings for Velocity.js are in `com.felstar.scalajs.velocity`

## Get started

To get started, run `sbt ~fastOptJS` in this example project. 

Obviously you need to have [SBT](http://www.scala-sbt.org/) installed. 

This should
download dependencies and prepare the relevant javascript files. It then kicks off a web server to serve the html and JS files.

If you open
[localhost:12345/target/scala-2.11/classes/velocity-dev.html](http://localhost:12345/target/scala-2.11/classes/velocity-dev.html) in your browser, it will show you a sample app, doing various animations and Scala code. Its not meant to be pretty, simply instructive. Do open the Console to see debug messages. There are also 3 other code samples.

## Live Demo

[Basic velocity demo](http://dinofancellu.com/demo/scalajsVelocity/velocity-dev.html)

[SVG demo](http://dinofancellu.com/demo/scalajsVelocity/svg-dev.html)

[UI Pack demo](http://dinofancellu.com/demo/scalajsVelocity/uipack-dev.html)

[Morph button demo](http://dinofancellu.com/demo/scalajsVelocity/morph-dev.html)

## Development

If you change your source HTML (inside `/src/main/resources`) or Scala (inside `/src/main/scala/example`), sbt will recompile as needed. 
You then just have to refresh the page to see the new version. Hopefully having an example application will make it clearer on how to use Velocity.js from Scala, i.e. monkey see, monkey do.

## The optimized version

Run `sbt fullOptJS` for an optimized version
of the final application, useful for final publication. You may well need to copy over `XXX-dev.html` to get your latest changes. Be sure to refer to the correct JS as well, as it will have a different name than the fast compiled version. e.g.

	<script type="text/javascript" src="../scala-js-velocity-js-example-opt.js"></script>

## Eclipse integration

If you want to edit in Eclipse (can't compile yet, but still very useful having full IDE with code completion), just run `sbt eclipse` the open the generated .project file inside eclipse. Keep sbt running in order to do the JS Compile. [https://github.com/typesafehub/sbteclipse/wiki](https://github.com/typesafehub/sbteclipse/wiki)

## Status

This is just a quick proof of concept and some bindings for those wanting to use Velocity.js from Scala.js. Feel free to get in contact with any issues etc.


