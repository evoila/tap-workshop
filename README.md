# TAP Workshop

This repository provides workflows for different tasks and serves as a guide. The workshop covers developer-tasks as
well as certain operator tasks.
Each directory covers a different task/feature of TAP and contains its own readme (providing workflows and useful
commands) and example application (where appropriate) with a workload for deploying the application from this 
repository.

## Covered features/tasks for developers
- [Using Accelerators](accelerator/README.md)

    This directory contains a guide on how to use accelerators and examples for manipulating text files, detached  from
    any programming language.
- [Adding Entities to the TAP Developer Portal Catalog](catalog/README.md)

  _Catalog_ contains some example entities for registering them in the developer portal as well as a short
instruction on how to do so. The developer portal is part of a TAP cluster and provided through
[Backstage](https://backstage.io) with the goal
to provide an overview about the Components, APIs, Users, Groups etc. within the TAP cluster.
- [Example-Java-Application](Example-Java-Application/README.md)

  _Example-Java-Application_ contains a sample Java application, a corresponding workload.yaml and a accelerator.yaml
- [Using PrivateGit Repositories](private-git/README.md)

  This directory contains a small guide on how to set up a private Git repository to be used by TAP and a secret.yaml
as template for providing a secret in the Kubernetes Cluster where TAP is running.
- [Providing Techdocs for Catalog Entities in the TAP Developer Portal](techdocs/README.md)

  _Techdocs_ covers, how documentation-webpages (so called _techdocs_) for an application can be provided in the TAP
developer portal
- [Using-AppSSO](using-appsso/README.md)

  Here, you find a simple example on how to use an AppSSO service provided through a TAP service class.
- [Using-Services](using-services/README.md)

  _Using-Services_ covers on how to use claimed services (like a database) in general. There is also an example that
uses the Bitnami MongoDB service class that comes pre-installed in a new TAP cluster.