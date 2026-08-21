FROM ubuntu:latest
LABEL authors="melvi"

ENTRYPOINT ["top", "-b"]