FROM selenium/standalone-chrome-debug

USER root

RUN apt-get update \
    && apt-get install -yq openjdk-11-jre-headless maven \
    && apt-get clean && rm -rf /var/lib/apt/lists/* /tmp/*

