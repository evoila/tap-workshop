# Using Private Git Repositories

In order for TAP to access a private Git repository you need to

1. Create a (fine-grained) token that has read-access to the repository
2. Fill in the secret.yaml the name of the secret, the owner of the token (username) and the token value (password) and create the
secret in the Kubernetes cluster in the namespace you want to create the workload. If you use the secret.yaml file
provided in this directory the command would be (namespace can be set with _-n_)
```shell
kubectl apply -f secret.yaml 
```
3. Create a workload that points to the private repository to the chosen branch. For aid you can use the workload.yaml
found in this directory (in this case you have to change the values found under 
spec.source and remove the subPath and you can probably remove spec.env). In the workload.yaml set the value in
_spec.params.gitops_ssh_secret_ to the name of the previously created secret in order to reference it.