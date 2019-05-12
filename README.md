# Blog OCTOPUS

## About

blog octopus是一个稳定、高性能、支持多平台的博客抓取工具，只需要指定相应ID，就可以抓取到全部文章。

## Features
  * 多平台支持：简书、博客园、CSDN、掘金、SegmentFault
  * 自定义抓取内容
  * 多种输出方式：html、markdown(TODO: pdf)
  
## Quick Start

  ### 1、Download and import.
  下载最新的发布版本，并引入到你的项目中。
  ### 2、Enjoy it like this.
  
  ```
  Octopus octopus = Octopus.fromSingle(BlogPlatform.CSDN, "target").threadPerSite(1);
  octopus.start();
  ```
  默认情况下会输出该作者所有博客的信息。
  
## More
  ### 1、批量导入
  ```
  Map<BlogPlatform, Collection<String>> all = new HashMap<>();
  all.put(BlogPlatform.CSDN, Arrays.asList("lonely_fireworks", "so", "on"));
  all.put(BlogPlatform.BOKEYUAN, Arrays.asList("binyue"));
  Octopus octopus = Octopus.fromMap(all);
  octopus.start();
  ```
  
  ### 2、指定抓取内容
  ### 3、获取抓取结果
  * 1
  * 2
  
  