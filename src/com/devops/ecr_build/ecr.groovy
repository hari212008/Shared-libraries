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
		sh 'sudo ls -lrth erv/src/Deployment/'
		echo "$imagename"			
		sh 'bash erv/src/Deployment/build_and_push.sh ecr v1'	
 // println "\u001B[32m [INFO] Copying EDA&Data processing scripts. Please wait...\u001B[0m "
      //  sh 'sudo cp -R erv/ /jenkins/terraform/modules/usecase-setup/source'
    //  println "\u001B[32m [INFO] \u001B[0m: Copying EDA&Data processing scripts is \u001B[32m SUCCESSFULL\u001B[0m."
    }
catch (Exception error) {
      println "\u001B[41m [ERROR] failed to copying EDA&DATA."
      throw error
    }
  }

}


