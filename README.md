Master: [![Build Status: master][travis-badge-master]][ci]
Dev: [![Build Status: dev][travis-badge-dev]][ci]
[![Version](http://img.shields.io/badge/version-3.0.1-blue.svg?style=flat)](https://github.com/MICommunity/psi-jami/tree/psi-jami-3.0.1)

Markdown | Less | Pretty
--- | --- | ---
*Still* | `renders` | **nicely**
1 | 2 | 3

#PSI-JAMI

###Introduction

The aim of JAMI is to provide a single java library and framework which unifies the standard formats such as PSI-MI XML and PSI-MITAB.

The JAMI model interfaces are abstracted from both formats to hide the complexity/requirements of each format. The development of softwares and tools on top of this framewrok simplifies the integration and support of the two standard formats.

It avoids endless conversions to different formats and avoid code/unit test duplicate as the code becomes more modular.

The utils package contains a collection of various helper classes. We're not sure if all of this classes or really necessary, but we need to use them for now. 

<img width='800' src='wiki/images/jamiIntroduction.png' />

# JAMI datasources/writers #

<img width='800' src='wiki/images/jami_datasources.png' />

# JAMI modules #

<img width='800' src='wiki/images/jami_existing_modeules.png' />
[travis-badge-master]: https://travis-ci.org/MICommunity/psi-jami.svg?branch=master
[travis-badge-dev]: https://travis-ci.org/MICommunity/psi-jami.svg?branch=develop
[ci]: https://travis-ci.org/MICommunity/psi-jami
