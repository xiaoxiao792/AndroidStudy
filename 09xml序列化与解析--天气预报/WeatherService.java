package cn.itcast.weather;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.util.Xml;

public class WeatherService {
	//返回天气信息的集合
		public static List<WeatherInfo> getWeatherInfos(InputStream is) 
	throws Exception {
			//得到pull解析器
			XmlPullParser parser = Xml.newPullParser();
			// 初始化解析器,第一个参数代表包含xml的数据
			parser.setInput(is, "utf-8");
			List<WeatherInfo> weatherInfos = null;
			WeatherInfo weatherInfo = null;
			//得到当前事件的类型
			int type = parser.getEventType();
			// END_DOCUMENT文档结束标签 
			while (type != XmlPullParser.END_DOCUMENT) {
				switch (type) {
				//一个节点的开始标签
				case XmlPullParser.START_TAG:
					//解析到全局开始的标签 infos 根节点
					if("infos".equals(parser.getName())){
						weatherInfos = new ArrayList<WeatherInfo>();
					}else if("city".equals(parser.getName())){
						weatherInfo = new WeatherInfo();
						String idStr = parser.getAttributeValue(0);
						weatherInfo.setId(Integer.parseInt(idStr));
					}else if("temp".equals(parser.getName())){
					     //parset.nextText()得到该tag节点中的内容
						String temp = parser.nextText();
						weatherInfo.setTemp(temp);
					}else if("weather".equals(parser.getName())){
						String weather = parser.nextText();
						weatherInfo.setWeather(weather);
					}else if("name".equals(parser.getName())){
						String name = parser.nextText();
						weatherInfo.setName(name);
					}else if("pm".equals(parser.getName())){
						String pm = parser.nextText();
						weatherInfo.setPm(pm);
					}else if("wind".equals(parser.getName())){
						String wind = parser.nextText();
						weatherInfo.setWind(wind);
					}
					break;

				//一个节点结束的标签
				case XmlPullParser.END_TAG:
					//一个城市的信息处理完毕，city的结束标签
					if("city".equals(parser.getName())){
						//一个城市的信息 已经处理完毕了.
						weatherInfos.add(weatherInfo);
						weatherInfo = null;
					}
					break;
				}
				//只要不解析到文档末尾，就解析下一个条目。得到下一个节点的事件类型
				//注意，这个一定不能忘，否则会成为死循环
				type = parser.next();
			}
			return weatherInfos;
		}

}
