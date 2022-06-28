# Fibonacci

Fibonacci was an interview assignment done by me, Gustavo Albino.

I decided to take a simpler approach, creating just a controller file and adding the endpoint to it, making the calculation there.

To guarantee perfomance, I chose to use an in-memory cache, what would also allow the calculations to go above 1k (n). It is even possible to go higher by incrementing 1k to 1k.
- e.g. request to n=1000 -> request to n=2000 -> and so on

There was also another possibility that was to use matrix to calculate fibonacci, but I decided to keep it simple and use recursion.

A few unit test were created just to illustrate.

If the number ends up being too high without caching it before, the application will just stackoverflow and load forever.

Talking about the yaml file, I did set the resources low on purpose so that it is easier to load test and validate the autoscaling. Also added the Health Check as a readiness check to guarantee that if the container can't receive requests, the loadbalancer will move the request to another container.

As for the Liveness check, I just added an entry testing if the process is still running. If it is, then it's fine.

## Prerequisites
- Docker;
- Minikube with metrics-server activated.
  - To start it, you got to run ```minikube addons enable metrics-server```

## Usage

To simplify the usage, I created a .sh file to make it easier to create the docker image, deploy it and apply it.

To run it, all you got to do is:
```bash
sh deploy.sh
```
After deploying the app, you would have to get the URL using the following:
```bash
minikube service fib --url
```
With that URL in hands, you can test the endpoint by calling the following url:
```
http://<ip>/fib?n=<number>
```