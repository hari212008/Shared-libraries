package com.devops.scripts


def pyt()
{
wrap([$class: 'AnsiColorBuildWrapper']) {
    try {
                println "\u001B[32m [INFO] Invoking traninig Job Please wait...\u001B[0m "
                dir('erv/src/') {
		sh label: '', script: 'sh python.sh'
        }
    }
catch (Exception error) {
      println "\u001B[41m [ERROR] failed to Invoke Traning Job"
      throw error
    }
  }

}


