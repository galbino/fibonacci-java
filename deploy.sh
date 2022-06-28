eval $(minikube docker-env)
docker build -t fib .
kubectl apply -f fib.yaml
