#/bin/sh
export GRAFANA_UID=$UID
export GRAFANA_GID=$GID
docker-compose -f docker-compose.yml -f docker-compose.linux.yml up
