![roughcljs-logo](https://github.com/frozar/roughcljs/blob/master/logo_roughcljs.svg "RoughCLJS logo")

`RoughCLJS` is a wrapper around the nice drawing library `RoughJS`:
https://roughjs.com/

The purpose is to provide a simple clojurescript interface to get shape
generated by `RoughJS` without messing around JS object, data structure,
method, field, integration, etc.

`RoughCLJS` wrap only the svg part of the `RoughJS` library.

To use `RoughCLJS` in an existing project you add this to your dependencies:

[![Clojars Project](https://clojars.org/org.clojars.frozar/roughcljs/latest-version.svg)](https://clojars.org/org.clojars.frozar/roughcljs)

This project has been generated by:

```shell
npx create-cljs-project roughcljs
```

## Compilation

To compile the project:
```shell
shadow-cljs compile lib
```

## Test

Thes tests are launched through Karma. To compile and launch them:
```shell
shadow-cljs watch ci && karma start
```

## Publish the lib on clojar

To generate the clojars package:
```shell
lein jar
lein deploy clojars
```
