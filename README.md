# gititon

Proof of concept Cloud Native Git server.

Hosts the resource repository from `src/main/resources/repo.tar.gz`
inside of a [Spring Boot app](http://localhost:8080/git/repo.git)

## Quickstart

1. In one terminal session run `./gradlew bootRun`
1. In another terminal session run `git clone http://localhost:8080/git/repo.git`

## the hosted repo

This artifact should
- be a .tar.gz file
- be pathed at the root of the git repo
    1. `(cd path/to/repo && tar -cvzf ../repo.tar.gz .)`
