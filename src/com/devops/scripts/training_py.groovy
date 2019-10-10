package com.devops.scripts
void setValue(String script, String path)
{
env.script_name = script
this.dir_path = path
}
if path == "null"
{
this.dir_path = $WORKSPACE
}
def pyt()
{
wrap([$class: 'AnsiColorBuildWrapper']) {
    try {
                println "\u001B[32m [INFO] Invoking traninig Job Please wait...\u001B[0m "
                dir('$dir_path') {
		sh label: '', script: 'sh ${script_name}'
        }
    }
catch (Exception error) {
      println "\u001B[41m [ERROR] failed to Invoke Traning Job"
      throw error
    }
  }

}


