# Clojure Prometheus Demo

Docker Compose stack consisting of Prometheus, Grafana and Clojure demo service.

The repository contains a ready to use Grafana DB which gets mounted as a volume into the docker container.

## Demo Service

The demo service compiles crime statistics for a UK postcode. It uses *postcodes.io* to resolve a postcode into coordinates and passes those on to *data.police.uk*.


## Prerequisites

- Docker
- Docker Compose https://docs.docker.com/compose/install/#install-compose

## Running

- on Linux run `up-linux.sh`. This will make sure that Grafana runs with your UID to be able to access the DB.
- on Mac OS `docker-compose up` should do the trick.

To lookup crime stats for Stanmore Tube station: `curl http://localhost:8080/crime/HA74PD`

You can login to Grafana on http://localhost:3000 with username `admin` and password `admin`.

The Prometheus UI is on http://localhost:9090








