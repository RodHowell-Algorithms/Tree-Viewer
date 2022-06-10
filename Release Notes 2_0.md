# Tree Viewer Version 2.0 Release Notes

Version 1.0 was hosted for many years at Kansas State University. When it was moved to GitHub, several changes were made to modernize the code. Wherever possible, backward compatibility was maintained.

## Known Incompatibility

The only known incompatibility with version 1.0 is that the application will no longer run as an applet.

## Changes

- Most of the types have been made serializable.
- Two new generic types, [**GenericConsList\<E\>**](https://rodhowell-algorithms.github.io/Tree-Viewer/doc/edu/ksu/cis/viewer/GenericConsList.html) and [**GenericStack\<E\>**](https://rodhowell-algorithms.github.io/Tree-Viewer/doc/edu/ksu/cis/viewer/GenericStack.html), have been added. While the original [**ConsList**](https://rodhowell-algorithms.github.io/Tree-Viewer/doc/edu/ksu/cis/viewer/ConsList.html) and [**Stack**](https://rodhowell-algorithms.github.io/Tree-Viewer/doc/edu/ksu/cis/viewer/Stack.html) classes have been retained for backward compatibility, but they are no longer used in the remainder of the code.