## Project description
One more example plugin for [marvin](https://github.com/beolnix/marvin/) bot.
Plugin displays count down for New Year and Salary

### Supported commands
!ny - for New Year count down
!money - for salary count down

## Project details
| Version | State | Source code |
| --- | --- | --- | --- |
| 0.2 | Stable | [0.2-release](https://github.com/beolnix/marvin-newyear-plugin/releases/tag/0.2-release) |
| 0.3-SNAPSHOT | In dev | [master](https://github.com/beolnix/marvin-newyear-plugin) |

## Requirements
#### To run
* JDK 8 only

#### To build
* JDK 8
* Gradle 2.8
* Groovy 2.4.4

## Build from source
Just execute the following command and may the force be with you:
```
gradle clean build
```

If everything is fine, you find **newyear-plugin-0.1.jar** in **build/libs/** 

## Usage 
To deploy the plugin simply copy it to the plugins directory of [marvin](https://github.com/beolnix/marvin/) bot.
No restart is required, marvin will pick it up on the fly and tell you about it in his **logs/application-main.log**.
Once it is deployed simply send a message directly to the bot or to the conference with it.

## Troubleshooting
* Be careful with content in **MANIFEST.MF**. Most likely plugin can't be deployed because of format violations in it. In the gradle script of this example special magic happens with version before it is saved in **MANIFEST.MF**. It is better to do just the same in your plugin.
* Another possible reason is in classpath specified in **MANIFEST.MF**. It is better to keep it constructed automatically as it is implemented in gradle script of this plugin.