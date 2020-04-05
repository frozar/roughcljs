## Unreleased

## 0.2.0 - 2020-04-05
### Fixed
 - Calling of `roughcljs.core/path` without the `rough-option` `:fill` crashed
   the library. To fix it, the present of `:fill` option is checked. The skeleton
   of the result returned by this function depends on the present of the `:fill` option.

## 0.1.1 - 2020-03-14
### Changed
 - Update the documentation, the build targets, the example project.
 - Update the reagent version.
 - Put in place the continous integration with circleci.
 - Fix a security issue by updating minimist package dependency.

## 0.1.0 - 2020-03-11
### Added
- First version of wrapping clojurescript function around roughjs.
