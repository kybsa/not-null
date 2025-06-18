FROM mcr.microsoft.com/devcontainers/java:17-bookworm

RUN apt-get update && apt-get install -y curl zip unzip
RUN curl -s "https://get.sdkman.io" | bash
RUN echo "source /usr/local/sdkman/bin/sdkman-init.sh" >> /etc/bash.bashrc
ENV SDKMAN_DIR="/usr/local/sdkman"
ENV PATH="$SDKMAN_DIR/bin:$PATH"
RUN bash -c "source /usr/local/sdkman/bin/sdkman-init.sh && sdk install java 21.0.6-amzn"
RUN bash -c "source /usr/local/sdkman/bin/sdkman-init.sh && sdk install maven 3.9.10"

