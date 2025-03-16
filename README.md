docker-compose -f docker-compose.yaml up -d --build --force-recreate
docker build -t alexandreqrz/rango:latest . &&
docker push alexandreqrz/rango:latest

play na nave
docker-compose -f docker-compose.yaml up -d

//prometheus 
http://localhost:9090

//grafana
http://localhost:3000