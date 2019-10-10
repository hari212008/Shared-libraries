package com.devops.ecr_build

void setValue(String image_name)
{
this.imagename = image_name
}


def push()
{
wrap([$class: 'AnsiColorBuildWrapper']) {
    try {
		println "\u001B[32m [INFO] Copying Build and push processing scripts. Please wait...\u001B[0m "    
	//	dir('erv/src/Deployment/')
		sh 'ls -lrth /erv/src/Deployment/'.execute()
		echo "$imagename"
		sh '$(aws ecr get-login)'			
		sh 'bash erv/src/Deployment/build_and_push.sh ecr v$BUILD_ID'
    }
catch (Exception error) {
      println "\u001B[41m [ERROR] failed to copying EDA&DATA."
      throw error
    }
  }

}


