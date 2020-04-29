package cn.itcast.weather;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.util.Xml;

public class WeatherService {
	//����������Ϣ�ļ���
		public static List<WeatherInfo> getWeatherInfos(InputStream is) 
	throws Exception {
			//�õ�pull������
			XmlPullParser parser = Xml.newPullParser();
			// ��ʼ��������,��һ�������������xml������
			parser.setInput(is, "utf-8");
			List<WeatherInfo> weatherInfos = null;
			WeatherInfo weatherInfo = null;
			//�õ���ǰ�¼�������
			int type = parser.getEventType();
			// END_DOCUMENT�ĵ�������ǩ 
			while (type != XmlPullParser.END_DOCUMENT) {
				switch (type) {
				//һ���ڵ�Ŀ�ʼ��ǩ
				case XmlPullParser.START_TAG:
					//������ȫ�ֿ�ʼ�ı�ǩ infos ���ڵ�
					if("infos".equals(parser.getName())){
						weatherInfos = new ArrayList<WeatherInfo>();
					}else if("city".equals(parser.getName())){
						weatherInfo = new WeatherInfo();
						String idStr = parser.getAttributeValue(0);
						weatherInfo.setId(Integer.parseInt(idStr));
					}else if("temp".equals(parser.getName())){
					     //parset.nextText()�õ���tag�ڵ��е�����
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

				//һ���ڵ�����ı�ǩ
				case XmlPullParser.END_TAG:
					//һ�����е���Ϣ������ϣ�city�Ľ�����ǩ
					if("city".equals(parser.getName())){
						//һ�����е���Ϣ �Ѿ����������.
						weatherInfos.add(weatherInfo);
						weatherInfo = null;
					}
					break;
				}
				//ֻҪ���������ĵ�ĩβ���ͽ�����һ����Ŀ���õ���һ���ڵ���¼�����
				//ע�⣬���һ����������������Ϊ��ѭ��
				type = parser.next();
			}
			return weatherInfos;
		}

}
