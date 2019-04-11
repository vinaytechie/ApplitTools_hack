FROM gitpod/workspace-full-vnc

USER gitpod

RUN  bash -c ". /home/gitpod/.sdkman/bin/sdkman-init.sh && sdk default java 11.0.2-open"

USER root
RUN apt-get update \
    && apt-get install -y openjfx libopenjfx-java matchbox awesome openbox \
    && apt-get clean && rm -rf /var/cache/apt/* && rm -rf /var/lib/apt/lists/* && rm -rf /tmp/*