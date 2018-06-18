docker stop banco
docker stop app
docker rm banco
docker rm app
docker rmi -f ricardojob/app
docker rmi -f ricardojob/banco


