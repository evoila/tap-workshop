# Services

This guide covers how to use an existing service class to create a service instance. Afterwards, an application with a service binding will be created in order to retrieve the credentials for accessing the service instance.

Additionally, this directory contains an example Java Application that uses a MongoDB Class-Claim for simple database
operations and the _API Auto Registration_ feature for creating API Docs in the developer portal (by using
[springdoc-openapi](https://springdoc.org)).

## Managing Services

You can list all available service classes with

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

In order to mount the values of the class-claim into an application, a _serviceClaim_ has to be provided in the workload under _spec_.
The following shows an example of a service claim within a workload:

```yaml
spec:
  serviceClaims:
  - name: name-that-is-reflected-inside-container
    ref:
      apiVersion: services.apps.tanzu.vmware.com/v1alpha1
      kind: ClassClaim
      name: name-of-class-claim
  ...
```

Since the class-claims comply with the [Service Binding Specification for Kubernetes](https://github.com/servicebinding/spec), all referenced bindings are mounted from their corresponding Kubernetes secrets into the directory _$SERVICE_BINDING_ROOT_ within the container. Each binding has its own subdirectory. Within those subdirectories, each key-value pair from the secret has its own file (key becomes the filename, value becomes the file content). In addition to directly accessing the contents by reading file contents from within the application, several programming languages have libraries for accessing the bindings. A list with examples can be found [here](https://servicebinding.io/application-developer/).

If your service isn't supported by the libraries and you are not sure about the credential names you can either:

- Check out the secret containing the base64 encoded credentials. The name of the secret can be found by getting the class-claim under _Claimed Resource.Name_
- Directly access the pod (name can be found through getting the workload) and checking out _$SERVICE_BINDING_ROOT_ with

```sh
kubectl exec -it $POD_NAME  -- /bin/bash
```

### Binding Secrets

It is also possible to bind secrets. This is done in a similar way to creating service-claims for ClassClaims: Instead 
of using _apiVersion: services.apps.tanzu.vmware.com/v1alpha1_, use _apiVersion: v1_ and instead of _kind: ClassClaim_,
use _kind: Secret_ while referring to the name of the secret instead of the name of a ClassClaim.
The following shows an example of binding a secret:
```shell
  serviceClaims:
  - name: name-that-is-reflected-inside-container
    ref:
      apiVersion: v1
      kind: Secret
      name: name-of-the-secret
```

## API Auto Registration

A Swagger documentation for an API can be created automatically. The requirement to do so is an endpoint that provides
API docs as YAML or JSON.

In order to automatically register the api, add a parameter to the workload spec with the name _api_descriptor_ like
shown in the example:

```yaml
spec:
  params:
    - name: api_descriptor
      value:
        type: openapi
        location:
          path: "/v3/api-docs"
        system: system-name
        owner: owner-name
        description: "Description as shown in the developer portal"
  ...
```

Creating the workload will now automatically create an API entity and a Swagger documentation in the developer portal.
Additional information can be found [here](https://docs.vmware.com/en/VMware-Tanzu-Application-Platform/1.7/tap/api-auto-registration-usage.html).
