1.First login to google cloud.
2.Then search kubernetes engine enable it.
3.Download google cloud cli setup. Simultaenously in terminal run this (New-Object Net.WebClient).DownloadFile("https://dl.google.com/dl/cloudsdk/channels/rapid/GoogleCloudSDKInstaller.exe", "$env:Temp\GoogleCloudSDKInstaller.exe")
& $env:Temp\GoogleCloudSDKInstaller.exe
4.Now it will be installed you will be asked to login type y.
5.Choose your project.
6.Type n for default region and zone.
7.Run gcloud init command.
8.Choose [1] Re-initialize this configuration [default] with new settings.
9.Select account.
10.Choose your poeject and you are good to go.
11.Install plugin by running command gcloud components install gke-gcloud-auth-plugin  