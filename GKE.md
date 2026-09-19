Important: Set the path to Google cloud sdk then you can use google cloud cli.
1.First login to google cloud on web.
2.Then search kubernetes engine enable it.
3.Download google cloud cli setup. Simultaenously in terminal run this (New-Object Net.WebClient).DownloadFile("https://dl.google.com/dl/cloudsdk/channels/rapid/GoogleCloudSDKInstaller.exe", "$env:Temp\GoogleCloudSDKInstaller.exe")
& $env:Temp\GoogleCloudSDKInstaller.exe
4.Now it will be installed you will be asked to login type y.
5.Choose your project.
6.Type n for default region and zone.
7.Run gcloud init command.
8.Choose [1] Re-initialize this configuration [default] with new settings.
9.Select account.
10.Choose your project and you are good to go.
11.Install plugin by running command gcloud components install gke-gcloud-auth-plugin
12.Create a clustor by going to Kubernetes engine select clusters -> select auopilot one set name region southeastasia1 then next next create.
13.Now connect to your google clustor using cli by command gcloud container clusters get-credentials <CLUSTER_NAME> --region=<REGION_NAME>

IN GKE image running doesnt get updated itself if you doesnt have image:tag, it will basically 
start the cached image again so have to explicitly use below command and also change version in pom.xml
then it will work properky if you want to automate use github actions.
kubectl set image deployment/config-server config-server=jakhar0005/connect-sphere-config-server:0.0.5-SNAPSHOT