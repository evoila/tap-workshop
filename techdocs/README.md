# Generating TechDocs

## Requirements
- Nodejs
- Additionally, the TechDocs-CLI can be installed with

```shell
npm install -g @techdocs/cli
```

## Generating and publishing TechDocs

```shell
#Generate TechDocs sites with TechDocs installed
techdocs-cli generate --source-dir ./ --output-dir ./site
#Without installation
npx @techdocs/cli generate --source-dir ./ --output-dir ./site

#Publish generated TechDocs sites to bucket with TechDocs installed
techdocs-cli publish --publisher-type awsS3 --storage-name <BUCKET/CONTAINER_NAME> --entity default/component/<COMPONENT_NAME> #--awsEndpoint <URL> --awsS3ForcePathStyle 
#Without installation
npx @techdocs/cli publish --publisher-type awsS3 --storage-name <BUCKET/CONTAINER_NAME> --entity default/component/<COMPONENT_NAME> #--awsEndpoint <URL> --awsS3ForcePathStyle 
```