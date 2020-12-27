FROM adoptopenjdk AS builder
RUN apt-get update \
&& apt-get install -y --no-install-recommends \
git \
binutils \
ca-certificates \
&& rm -rf /var/lib/apt/lists/*

RUN git clone https://github.com/sharadprsn/reactiveApp.git
WORKDIR /reactiveApp/
RUN chmod +x ./gradlew && ./gradlew clean build runtimezip

FROM alpine:latest
RUN apk --no-cache add ca-certificates
WORKDIR /root/
COPY --from=builder /reactiveApp/build/image .
CMD ["./bin/ReactiveApp"]