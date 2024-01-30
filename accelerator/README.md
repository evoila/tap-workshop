# Custom Accelerators

This directory contains a simple accelerator and the fragments used by it.

The Accelerator can be found in _accelerator.yaml_, the fragments under _fragments/_.

Additionally, _/Example-Java-Application/_ contains an accelerator for the application within.

## Testing out the Accelerators
 
First, the fragments have to be created:

```shell
#From local (command executed from this directory)
tanzu accelerator fragment create replace-eins --local-path . --source-image harbor.dieunkrauts.de/tap/edu-accelerator-mf --secret-ref reg-creds 

#From Git Repository
tanzu accelerator create workshop --git-repository https://github.com/JanKraut/tap-workshop/ --git-branch main --git-sub-path Example-Java-Application
```

Afterwards, the accelerator can be created:

```shell
#From local (command executed from this directory)
tanzu accelerator create workshop --local-path . --source-image harbor.dieunkrauts.de/tap/edu-accelerator-mf --secret-ref reg-creds 

#From Git Repository
tanzu accelerator create workshop --git-repository https://github.com/JanKraut/tap-workshop/ --git-branch main --git-sub-path Example-Java-Application
```

Additionally, the accelerator can be used locally (still requires the fragments to be available in the cluster)

```shell
tanzu accelerator generate-from-local --accelerator-path workshop=. --fragment-paths replace-eins=fragments/replace --options '{"replaceFourWith":"pizza", "replaceEins":"uno"}' --output-dir generated-project --server-url $DEVELOPERPORTAL
```