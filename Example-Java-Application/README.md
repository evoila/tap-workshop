# Example Application

In order to create a workload for the application in the TAP-Cluster, download the workload.yaml found under _config/_ 
and do a
```shell
tanzu apps workload apply --file ./workload.yml
```

## Using the accelerator locally

Accelerators are used to prepare provided code (like the code of the application within this directory) to be used as a
workload later by adding/manipulating data. Note that an accelerator can manipulate any file which means that there can
also be accelerators for data that does not have to do anything with code. An example on that can be found 
[here](../accelerator/README.md).
The accelerator in this directory simply sets the name of the workload in _config/workload.yaml_ and 
_catalog/component.yaml_ (which is used for registering an entity representing the workload in the developer portal - 
more information can be found [here](../catalog/README.md)).
For using the accelerator locally, execute the following command from within this directory. Keep in mind to set 
DEVELOPERPORTAL or to replace it with the actual url to the developer portal.
```shell
tanzu accelerator generate-from-local --options '{"workloadName":"myWorkload"}' --output-dir generated-project --server-url $DEVELOPERPORTAL --force --accelerator-path workshop=.
```

## Using the _getPythonServer_ Endpoint

In order to use the endpoint for accessing a python service, in config/workload.yaml replace the value under 
spec.env.SERVER_URL.