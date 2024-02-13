# Services

This guide covers how to use an existing service class to create a service instance. Afterwards, an application with a service binding will be created in order to retrieve the credentials for accessing the service instance.

## Managing Services

You can list all avilable service classes with

```sh
tanzu service class list
```

## Consuming/Claiming Services

### Creating Service Claims

Create a service claim with

```sh
tanzu service class-claim create mongodb-claim --class mongodb-unmanaged
```

The claim causes the creation of a service instance of mongodb.

Get information about the claim with

```sh
tanzu service class-claim get mongodb-claim
```

The newly created service instance can be found in its own namespace.

### Consuming Services through Service Claims

List all claims in the current namespace with

```sh
tanzu service class-claim list
```

TODO WORKLOAD

Since the class-claims comply with the [Service Binding Specification for Kubernetes](https://github.com/servicebinding/spec), all referenced bindings are mounted from their corresponding Kubernetes secrets into the directory _$SERVICE_BINDING_ROOT_ within the container. Each binding has its own subdirectory. Within those subdirectories, each key-value pair from the secret has its own file (key becomes the filename, value becomes the file content). In addition to directly accessing the contents by reading file contents from within the application, several programming languages have libraries for accessing the bindings. A list with examples can be found [here](https://servicebinding.io/application-developer/).

If your service isn't supported by the libraries and you are not sure about the credential names you can either:

- Check out the secret containing the base64 encoded credentials. The name of the secret can be found by getting the class-claim under _Claimed Resource.Name_
- Directly access the pod (name can be found through getting the workload) and checking out _$SERVICE_BINDING_ROOT_ with

```sh
kubectl exec -it $POD_NAME  -- /bin/bash
``` 

### Creating new Services
