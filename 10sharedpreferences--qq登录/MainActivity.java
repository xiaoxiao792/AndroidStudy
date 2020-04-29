package cn.itcast.serialize;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlSerializer;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Xml;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	private List<Person> userData; // �������ݵļ���
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// �����������ݵļ���,ģ�������
		userData = new ArrayList<Person>();
		for (int i = 0; i < 3; i++) {
			userData.add(new Person("��" + i, 100 - i, 80 - i));
		}
	}

	// ��Person���󱣴�Ϊxml��ʽ
	public void Serializer(View view) {
		try {
			XmlSerializer serializer = Xml.newSerializer();
			File file = new File(Environment.getExternalStorageDirectory(),
					"person.xml");
			FileOutputStream os = new FileOutputStream(file);
			serializer.setOutput(os, "UTF-8");
			serializer.startDocument("UTF-8", true);
			serializer.startTag(null, "persons");
			int count = 0;
			for (Person person : userData) {
				serializer.startTag(null, "person");
				serializer.attribute(null, "id", count + "");
                   //��Person�����name����д��XML�ļ�
				serializer.startTag(null, "name");
				serializer.text(person.getName());
				serializer.endTag(null, "name");
                   //��Person�����age����д��XML�ļ�
				serializer.startTag(null, "age");
				serializer.text(String.valueOf(person.getAge()));
				serializer.endTag(null, "age");
                       //��Person�����score����д��XML�ļ�
				serializer.startTag(null, "score");
				serializer.text(String.valueOf(person.getScore()));
				serializer.endTag(null, "score");
				serializer.endTag(null, "person");
				count++;
			}
			serializer.endTag(null, "persons");
			serializer.endDocument();
			serializer.flush();
			os.close();
			Toast.makeText(this, "�����ɹ�", 0).show();
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(this, "����ʧ��", 0).show();
		}
	}
}
