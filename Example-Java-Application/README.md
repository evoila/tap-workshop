# Example Application

## Using the accelerator locally

Use the following command from within this directory. Keep in mind to set DEVELOPERPORTAL or  to replace it with the actual url to the developer portal.
```shell
tanzu accelerator generate-from-local --options '{"workloadName":"myWorkload"}' --output-dir generated-project --server-url $DEVELOPERPORTAL --force --accelerator-path workshop=.
```