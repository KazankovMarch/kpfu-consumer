Тестовая площадка (центральная БД) assotiation
==============================

Для работы на локальной машине:
<br/>
приложение assotiation доступно по адресу http://localhost:8080/association
<br/>
Для работы через Internet:
<br/>
приложение assotiation доступно по адресу http://185.20.227.163:8080/association
<br/>
Для контроля содержимого базы данных центральной площадки доступны веб-страницы:<br/>
http://185.20.227.163:8080/association/gui/nomenclature<br/>
http://185.20.227.163:8080/association/gui/organization<br/>
http://185.20.227.163:8080/association/gui/request<br/>
http://185.20.227.163:8080/association/gui/offer<br/>
http://185.20.227.163:8080/association/gui/contract<br/>


Структура данных
==============================
<p>Структура данных отобажена в виде набора классов в пакете 
	<a href="https://github.com/ksenikeev/dadTestPlace/tree/master/association/src/main/java/ru/kpfu/icmit/association/model">ru.kpfu.icmit.association.model</a>. Этой структуре соответствуют теги XML документов для обмена информацией.
<p>В том случае, если ваша модель данных отличается от приведенной, ситуацию можно нормализовать с помощью аннотаций, например:
<br/>
<table>
	<th>Базовая модель</th>
	<th>XML документ</th>
	<th>"Своя" модель</th>
	<tbody>
	<tr>
		<td>
			class Nomenclature { <br/>
			...<br/>
			String productName;<br/>
			...<br/>
			String getProductName() {<br/>
				return productName;<br/>
			}<br/>
		</td>
		<td>			
			&lt;nomenclature&gt;<br/>
			&nbsp;&nbsp;&nbsp;...<br/>
			&nbsp;&nbsp;&nbsp;&lt;productName&gt;Уголь антрацит&lt;/productName&gt;<br/>
			&nbsp;&nbsp;&nbsp;...<br/>
			&lt;/nomenclature&gt;<br/>
		</td>
		<td>
			class Nomenclature { <br/>
			...<br/>
			String nomenclatureName;<br/>
			...<br/>
			<b>@XmlElement(value="productName")</b><br/>
			String getNomenclatureName() {<br/>
				return nomenclatureName;<br/>
			}<br/>
		</td>
	</tr>
	<tr></tr>
	<tr></tr>
</tbody>

</table>


Запрос на добавление организации:
==============================
<a href="https://github.com/ksenikeev/dadTestPlace/blob/master/association/src/test/java/AddNewOrganizationToRemoteBase.java">Тест запроса</a><br/>
`POST` на `/organization/add`
<br/>
`Content-Type: application/xml`
<br/>
в теле POST-запроса отправляем XML документ<br/>

&lt;?xml version="1.0" encoding="UTF-8" standalone="yes"?&gt;<br/>
&lt;envelope&gt;<br/>
&nbsp;&nbsp;    &lt;body&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;        &lt;organization&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;            &lt;adressOfOrganization&gt;г. Казань, ул. Университетская, д. 35&lt;/adressOfOrganization&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;            &lt;inn&gt;1600000001&lt;/inn&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;            &lt;kpp&gt;1601001&lt;/kpp&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;            &lt;ogrn&gt;1231231434351&lt;/ogrn&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;            &lt;nameOfOrganization&gt;Производитель 1&lt;/nameOfOrganization&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;            &lt;uid&gt;b5f5521e-bdbf-4a27-8e4b-d8de0c57127b&lt;/uid&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;        &lt;/organization&gt;<br/>
&nbsp;&nbsp;    &lt;/body&gt;<br/>
&nbsp;&nbsp;    &lt;header/&gt;<br/>
&lt;/envelope&gt;<br/>

Запрос на добавление номенклатуры:
==============================
<a href="https://github.com/ksenikeev/dadTestPlace/blob/master/association/src/test/java/AddNewNomenclatureToRemoteBase.java">Тест запроса</a><br/>
`POST` на `/nomenclature/add`
<br/>
в теле POST-запроса отправляем XML документ<br/>
&lt;?xml version="1.0" encoding="UTF-8" standalone="yes"?&gt;<br/>
&lt;envelope&gt;<br/>
&nbsp;    &lt;body&gt;<br/>
&nbsp;&nbsp;        &lt;nomenclature&gt;<br/>
&nbsp;&nbsp;&nbsp;            &lt;productName&gt;Стул офисный&lt;/productName&gt;<br/>
&nbsp;&nbsp;&nbsp;            &lt;uid&gt;9f7d5cf3-e139-4ba5-a60d-8eae58c076e1&lt;/uid&gt;<br/>
&nbsp;&nbsp;        &lt;/nomenclature&gt;<br/>
&nbsp;    &lt;/body&gt;<br/>
&nbsp;    &lt;header/&gt;<br/>
&lt;/envelope&gt;<br/>

Ответ приходит в виде XML документа (с добавлением тегов `createDate` и `modifyDate`)<br/>
<br/>
&lt;?xml version="1.0" encoding="UTF-8" standalone="yes"?&gt;<br/>
&lt;envelope&gt;<br/>
&nbsp;	&lt;header/&gt;<br/>
&nbsp;	&lt;body&gt;<br/>
&nbsp;&nbsp;		&lt;nomenclature&gt;<br/>
&nbsp;&nbsp;&nbsp;			&lt;id&gt;8&lt;/id&gt;<br/>
&nbsp;&nbsp;&nbsp;			&lt;createDate&gt;2019-12-21T08:30:29.995+03:00&lt;/createDate&gt;<br/>
&nbsp;&nbsp;&nbsp;			&lt;modifyDate&gt;2019-12-21T08:30:29.995+03:00&lt;/modifyDate&gt;<br/>
&nbsp;&nbsp;&nbsp;			&lt;productName&gt;Уголь антрацит&lt;/productName&gt;<br/>
&nbsp;&nbsp;&nbsp;			&lt;uid&gt;abbe5815-45e6-4b34-9cf6-a7273bb03bca&lt;/uid&gt;<br/>
&nbsp;&nbsp;		&lt;/nomenclature&gt;<br/>
&nbsp;	&lt;/body&gt;<br/>
&lt;/envelope&gt;<br/>

Запрос на получение списка номенклатуры, измененной после определенной даты:
==============================
<a href="https://github.com/ksenikeev/dadTestPlace/blob/master/association/src/test/java/GetNewNomenclatureAfterDate.java">Тест запроса</a><br/>
`POST` на `/nomenclature/get`
<br/>
параметр запроса `datefrom` со значение даты и времени в формате `yyyy-MM-dd'T'HH:mm:ss.SXXX` (URL encoded)
<br/>
Например, дата `2019-01-01T00:00:01.1+03:00` <br/>
в теле запроса: `datefrom=2019-01-01T00%3A00%3A01.1%2B03%3A00`
<br/>
Ответ возвращается в виде XML документа, содержащий список номенклатуры, измененной/добавленной в центральную площадку после указанной даты
<br/>
&lt;?xml version="1.0" encoding="UTF-8" standalone="yes"?><br/>
&lt;envelope&gt;<br/>
&nbsp;	&lt;body&gt;<br/>
&nbsp;&nbsp;		&lt;nomenclatures&gt;<br/>
&nbsp;&nbsp;&nbsp;			&lt;nomenclature&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;				&lt;createDate&gt;2019-12-14T09:57:07.650+03:00&lt;/createDate&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;				&lt;id&gt;3&lt;/id&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;				&lt;modifyDate&gt;2019-12-14T09:57:07.650+03:00&lt;/modifyDate&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;				&lt;productName&gt;3D принтер&lt;/productName&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;				&lt;uid&gt;aee07b8f-7eda-478f-8be2-9b7ac596c424&lt;/uid&gt;<br/>
&nbsp;&nbsp;&nbsp;			&lt;/nomenclature&gt;<br/>
&nbsp;&nbsp;&nbsp;			&lt;nomenclature&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;				&lt;createDate&gt;2019-12-14T09:59:52.397+03:00&lt;/createDate&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;				&lt;id&gt;4&lt;/id&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;				&lt;modifyDate&gt;2019-12-14T09:59:52.397+03:00&lt;/modifyDate&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;				&lt;productName&gt;Стол офисный&lt;/productName&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;				&lt;uid&gt;058b8777-1bc1-4b9c-8c95-34f0f3bd2623&lt;/uid&gt;<br/>
&nbsp;&nbsp;&nbsp;			&lt;/nomenclature&gt;<br/>
&nbsp;&nbsp;&nbsp;			&lt;nomenclature&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;				&lt;createDate&gt;2019-12-14T10:01:10.843+03:00&lt;/createDate&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;				&lt;id&gt;5&lt;/id&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;				&lt;modifyDate&gt;2019-12-14T10:01:10.843+03:00&lt;/modifyDate&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;				&lt;productName&gt;Стул офисный&lt;/productName&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;				&lt;uid&gt;9f7d5cf3-e139-4ba5-a60d-8eae58c076e1&lt;/uid&gt;<br/>
&nbsp;&nbsp;&nbsp;			&lt;/nomenclature&gt;<br/>
&nbsp;&nbsp;		&lt;/nomenclatures&gt;<br/>
&nbsp;	&lt;/body&gt;<br/>
&lt;/envelope&gt;<br/>

Отправка на центральную площадку запроса на приобретение товаров/услуг
==============================
<a href="https://github.com/ksenikeev/dadTestPlace/blob/master/association/src/test/java/AddNewRequestToRemoteBase.java">Тест запроса</a><br/>
`POST` на `/request/add`
<br/>
в теле POST-запроса отправляем XML документ<br/>
&lt;?xml version="1.0" encoding="UTF-8" standalone="yes"?&gt;&lt;envelope&gt;<br/>
&nbsp;    &lt;body&gt;<br/>
&nbsp;&nbsp;        &lt;request&gt;<br/>
&nbsp;&nbsp;&nbsp;            &lt;dateOfPerformance&gt;2020-01-12T00:00:00+03:00&lt;/dateOfPerformance&gt;<br/>
&nbsp;&nbsp;&nbsp;            &lt;nomenclature&gt;<br/>
&nbsp;&nbsp;&nbsp;                &lt;uid&gt;058b8777-1bc1-4b9c-8c95-34f0f3bd2623&lt;/uid&gt;<br/>
&nbsp;&nbsp;&nbsp;            &lt;/nomenclature&gt;<br/>
&nbsp;&nbsp;&nbsp;            &lt;organization&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;                &lt;inn&gt;1600000002&lt;/inn&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;               &lt;kpp&gt;1601001&lt;/kpp&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;            &lt;/organization&gt;<br/>
&nbsp;&nbsp;&nbsp;            &lt;priceOfProduct&gt;1250.0&lt;/priceOfProduct&gt;<br/>
&nbsp;&nbsp;&nbsp;            &lt;countOfProduct&gt;100.0&lt;/countOfProduct&gt;<br/>
&nbsp;&nbsp;&nbsp;            &lt;uid&gt;46c6074b-b93f-467f-af39-26ef27825020&lt;/uid&gt;<br/>
&nbsp;&nbsp;&nbsp;            &lt;unitCode&gt;piece&lt;/unitCode&gt;<br/>
&nbsp;&nbsp;        &lt;/request&gt;<br/>
&nbsp;    &lt;/body&gt;<br/>
&nbsp;    &lt;header/&gt;<br/>
&lt;/envelope&gt;<br/>


Получение всех запросов с определенной номенклатурой
==============================
<a href="https://github.com/ksenikeev/dadTestPlace/blob/master/association/src/test/java/GetRequestsByNomenclatureUid.java">Тест запроса</a><br/>
`POST` на `/request/getbynom`
<br/>
в теле POST-запроса отправляем XML документ с кодом номенклатуры<br/>
&lt;?xml version="1.0" encoding="UTF-8" standalone="yes"?&gt;<br/>
&lt;envelope&gt;<br/>
&nbsp;    &lt;body&gt;<br/>
&nbsp;&nbsp;        &lt;nomenclature&gt;<br/>
&nbsp;&nbsp;&nbsp;            &lt;uid&gt;9f7d5cf3-e139-4ba5-a60d-8eae58c076e1&lt;/uid&gt;<br/>
&nbsp;&nbsp;        &lt;/nomenclature&gt;<br/>
&nbsp;    &lt;/body&gt;<br/>
&nbsp;    &lt;header/&gt;<br/>
&lt;/envelope&gt;<br/>

Ответ приходит в виде XML документа со списком запросов в конверте<br/>
<br/>
&lt;?xml version="1.0" encoding="UTF-8" standalone="yes"?&gt;<br/>
&lt;envelope&gt;<br/>
&nbsp;&lt;	body&gt;<br/>
&nbsp;&nbsp;	&lt;requests&gt;<br/>
&nbsp;&nbsp;&nbsp;	&lt;request&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;	&lt;countOfProduct&gt;1000.0&lt;/countOfProduct&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;	&lt;dateOfPerformance&gt;2020-01-12T00:00:00+03:00&lt;/dateOfPerformance&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;	&lt;id&gt;2&lt;/id&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;	&lt;nomenclature&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	&lt;createDate&gt;2019-12-14T09:59:52.397+03:00&lt;/createDate&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	&lt;id&gt;4&lt;/id&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	&lt;modifyDate&gt;2019-12-14T09:59:52.397+03:00&lt;/modifyDate&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	&lt;productName&gt;Стол офисный&lt;/productName&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	&lt;uid&gt;058b8777-1bc1-4b9c-8c95-34f0f3bd2623&lt;/uid&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;	&lt;/nomenclature&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;	&lt;organization&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	&lt;adressOfOrganization&gt;г. Казань, ул. Университетская, д. 35&lt;/adressOfOrganization&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	&lt;id&gt;4&lt;/id&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	&lt;inn&gt;1600000002&lt;/inn&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	&lt;kpp&gt;1601001&lt;/kpp&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	&lt;nameOfOrganization&gt;Производитель 2&lt;/nameOfOrganization&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	&lt;uid&gt;16cb67df-9b2a-4dad-8410-c6c727b3ea42&lt;/uid&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;	&lt;/organization&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;	&lt;priceOfProduct&gt;1250.0&lt;/priceOfProduct&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;	&lt;uid&gt;2b705462-45ef-4012-8488-f394897d3967&lt;/uid&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;	&lt;unitCode&gt;piece&lt;/unitCode&gt;<br/>
&nbsp;&nbsp;&nbsp;	&lt;/request&gt;<br/>
&nbsp;&nbsp;	&lt;/requests&gt;<br/>
&nbsp;	&lt;/body&gt;<br/>
&nbsp;	&lt;header/&gt;<br/>
&lt;/envelope&gt;<br/>



Отправка на центральную площадку предложения товаров и услуг товаров/услуг
==============================
<a href="https://github.com/ksenikeev/dadTestPlace/blob/master/association/src/test/java/AddNewOfferToRemoteBase.java">Тест запроса</a><br/>
`POST` на `/offer/add`
<br/>
в теле POST-запроса отправляем XML документ<br/>
&lt;?xml version="1.0" encoding="UTF-8" standalone="yes"?&gt;&lt;envelope&gt;<br/>
&nbsp;    &lt;body&gt;<br/>
&nbsp;&nbsp;        &lt;offer&gt;<br/>
&nbsp;&nbsp;&nbsp;            &lt;dateOfPerformance&gt;2020-01-02T00:00:00+03:00&lt;/dateOfPerformance&gt;<br/>
&nbsp;&nbsp;&nbsp;            &lt;nomenclature&gt;<br/>
&nbsp;&nbsp;&nbsp;                &lt;uid&gt;058b8777-1bc1-4b9c-8c95-34f0f3bd2623&lt;/uid&gt;<br/>
&nbsp;&nbsp;&nbsp;            &lt;/nomenclature&gt;<br/>
&nbsp;&nbsp;&nbsp;            &lt;organization&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;                &lt;inn&gt;1600000002&lt;/inn&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;               &lt;kpp&gt;1601001&lt;/kpp&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;            &lt;/organization&gt;<br/>
&nbsp;&nbsp;&nbsp;            &lt;priceOfProduct&gt;1270.0&lt;/priceOfProduct&gt;<br/>
&nbsp;&nbsp;&nbsp;            &lt;countOfProduct&gt;1000.0&lt;/countOfProduct&gt;<br/>
&nbsp;&nbsp;&nbsp;            &lt;uid&gt;46c6074b-b93f-467f-af39-26ef27825020&lt;/uid&gt;<br/>
&nbsp;&nbsp;&nbsp;            &lt;unitCode&gt;piece&lt;/unitCode&gt;<br/>
&nbsp;&nbsp;        &lt;/offer&gt;<br/>
&nbsp;    &lt;/body&gt;<br/>
&nbsp;    &lt;header/&gt;<br/>
&lt;/envelope&gt;<br/>

Получение всех предложений с определенной номенклатурой
==============================
<a href="https://github.com/ksenikeev/dadTestPlace/blob/master/association/src/test/java/GetOffersByNomenclatureUid.java">Тест запроса</a><br/>
`POST` на `/offer/getbynom`
<br/>
в теле POST-запроса отправляем XML документ с кодом номенклатуры<br/>
&lt;?xml version="1.0" encoding="UTF-8" standalone="yes"?&gt;<br/>
&lt;envelope&gt;<br/>
&nbsp;    &lt;body&gt;<br/>
&nbsp;&nbsp;        &lt;nomenclature&gt;<br/>
&nbsp;&nbsp;&nbsp;            &lt;uid&gt;9f7d5cf3-e139-4ba5-a60d-8eae58c076e1&lt;/uid&gt;<br/>
&nbsp;&nbsp;        &lt;/nomenclature&gt;<br/>
&nbsp;    &lt;/body&gt;<br/>
&nbsp;    &lt;header/&gt;<br/>
&lt;/envelope&gt;<br/>

Ответ приходит в виде XML документа со списком предложений в конверте<br/>
<br/>
&lt;?xml version="1.0" encoding="UTF-8" standalone="yes"?&gt;<br/>
&lt;envelope&gt;<br/>
&nbsp;&lt;	body&gt;<br/>
&nbsp;&nbsp;	&lt;offers&gt;<br/>
&nbsp;&nbsp;&nbsp;	&lt;offer&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;	&lt;countOfProduct&gt;1000.0&lt;/countOfProduct&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;	&lt;dateOfPerformance&gt;2020-01-12T00:00:00+03:00&lt;/dateOfPerformance&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;	&lt;id&gt;2&lt;/id&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;	&lt;nomenclature&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	&lt;createDate&gt;2019-12-14T09:59:52.397+03:00&lt;/createDate&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	&lt;id&gt;4&lt;/id&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	&lt;modifyDate&gt;2019-12-14T09:59:52.397+03:00&lt;/modifyDate&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	&lt;productName&gt;Стол офисный&lt;/productName&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	&lt;uid&gt;058b8777-1bc1-4b9c-8c95-34f0f3bd2623&lt;/uid&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;	&lt;/nomenclature&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;	&lt;organization&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	&lt;adressOfOrganization&gt;г. Казань, ул. Университетская, д. 35&lt;/adressOfOrganization&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	&lt;id&gt;4&lt;/id&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	&lt;inn&gt;1600000002&lt;/inn&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	&lt;kpp&gt;1601001&lt;/kpp&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	&lt;nameOfOrganization&gt;Производитель 2&lt;/nameOfOrganization&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	&lt;uid&gt;16cb67df-9b2a-4dad-8410-c6c727b3ea42&lt;/uid&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;	&lt;/organization&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;	&lt;priceOfProduct&gt;1250.0&lt;/priceOfProduct&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;	&lt;uid&gt;2b705462-45ef-4012-8488-f394897d3967&lt;/uid&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;	&lt;unitCode&gt;piece&lt;/unitCode&gt;<br/>
&nbsp;&nbsp;&nbsp;	&lt;/offer&gt;<br/>
&nbsp;&nbsp;	&lt;/offers&gt;<br/>
&nbsp;	&lt;/body&gt;<br/>
&nbsp;	&lt;header/&gt;<br/>
&lt;/envelope&gt;<br/>

Добавление контракта
==============================
<a href="https://github.com/ksenikeev/dadTestPlace/blob/master/association/src/test/java/AddNewContractToRemoteBase.java">Тест запроса</a><br/>
`POST` на `/contract/add`
<br/>
в теле POST-запроса отправляем XML документ (в структурах: номенклатура, предложение, заявка достаточно указать только код UID)<br/>
&lt;?xml version="1.0" encoding="UTF-8" standalone="yes"?&gt;<br/>
&lt;envelope&gt;<br/>
&nbsp;    &lt;body&gt;<br/>
&nbsp;&nbsp;        &lt;contract&gt;<br/>
&nbsp;&nbsp;&nbsp;            &lt;cost&gt;1240.0&lt;/cost&gt;<br/>
&nbsp;&nbsp;&nbsp;            &lt;count&gt;890.0&lt;/count&gt;<br/>
&nbsp;&nbsp;&nbsp;            &lt;dateOfPerformance&gt;2020-01-12T00:00:00+03:00&lt;/dateOfPerformance&gt;<br/>
&nbsp;&nbsp;&nbsp;            &lt;nomenclature&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;                &lt;uid&gt;058b8777-1bc1-4b9c-8c95-34f0f3bd2623&lt;/uid&gt;<br/>
&nbsp;&nbsp;&nbsp;            &lt;/nomenclature&gt;<br/>
&nbsp;&nbsp;&nbsp;            &lt;offer&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;                &lt;uid&gt;c3f9b9a2-84f6-45c5-b4cf-8be7df2168a5&lt;/uid&gt;<br/>
&nbsp;&nbsp;&nbsp;            &lt;/offer&gt;<br/>
&nbsp;&nbsp;&nbsp;            &lt;request&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;                &lt;uid&gt;69254c5f-a125-45f4-8798-7f5e23894b04&lt;/uid&gt;<br/>
&nbsp;&nbsp;&nbsp;            &lt;/request&gt;<br/>
&nbsp;&nbsp;&nbsp;            &lt;uid&gt;eeb8278b-295c-42d4-bd1e-2d38677bb9e0&lt;/uid&gt;<br/>
&nbsp;&nbsp;        &lt;/contract&gt;<br/>
&nbsp;    &lt;/body&gt;<br/>
&nbsp;    &lt;header/&gt;<br/>
&lt;/envelope&gt;<br/>
<br/>


Получение всех контрактов с организацией
==============================
<a href="https://github.com/ksenikeev/dadTestPlace/blob/master/association/src/test/java/GetContractByOrganizationUid.java">Тест запроса</a><br/>
`POST` на `/contract/getbyorg`
<br/>


Вопросы к зачету
==============================
1. Подключение к СУБД PostgreSQL средствами языка Java|Python|C#|C++ <br/>
2. Выполнение операций DDL <br/>
3. Получение структуры таблицы программно.
4. Выполнение операций SQL INSERT, UPDATE, DELETE <br/>
5. Особенности выполнения SQL запросов SELECT. Работа с ResultSet (Java) | cursor (Python). <br/>
6. Использование подготовленных запросов для PostgreSQL. <br/>
7. Автоматическая генерация ID. Добавление нескольких связанных таблиц.  <br/>
8. Программное управление транзакциями. Уровни изоляции транзакций. <br/>
9. Работа с распределенными базами данных. Способы синхронизации данных. Способы выполнения запросов из распределенных баз.<br/>
10. Использование пула подключений к БД.<br/>
<br/>
Практическая часть:<br/>
1. Из данного файла в формате CSV уметь программно переносить данные в таблицу БД.<br/>
2. Сохранять программно консолидированные данные из нескольких таблиц в CSV файл.<br/>
3. Разработка приложения вносящего информацию, введенную с консоли, в БД.<br/>
4. Разработка приложения, выводящего информацию по интерактивно выбранным пользователем столбцам таблицы БД.<br/>
5. Разработка приложения, выводящего данные таблицы с учетом ограничений, введенных пользователем интерактивно.<br/>
6. Разработка приложения, удаляющего данные по указанным ключам, предложенных предварительно на выбор из БД.<br/>
<br/>
Программы должны быть реализованы в течении 60 минут на одном из языков Java, Python, C#, C++




