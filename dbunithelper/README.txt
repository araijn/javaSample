DB単体試験のためのツール「DBUnit」を簡素化して使うためのものです。

◯必要なもの
・jbunit-2.4.9.jar
・slf4j-api-1.5.6.jar
・slf-nop_1.4.3.jar
・apache poi-3.2.jar
※pom.xmlに必要なjarを定義しているため、それをeclipseで読み込むなり、mvn packageなりを実行すれば必要なjarはダウンロードされます。

◯用意するもの
・JDBCドライバ
・試験で投入するデータの定義（XMLフォーマット・XLSフォーマット）


◯基本的な使い方

  // テストデータのフォーマットがXMLの場合 FileType.XML
  // テストデータのフォーマットがXLSの場合 FileType.XLS
  DBUnitHelper helper = DBUnitHelperFactory.createSimpleHelper(FileType.XML, "sampletestdata.xml");

  helper.setup();

  { データベースの更新処理 }

  helper.cleanUp();


◯設定ファイル
  DBUnitHelper.properties
   データベースの接続情報を定義
   ※設定ファイルは自分のプロジェクトで使用しているファイルを指定することも可能です。
     その場合は、JDBCドライバ名、接続URL、ユーザ名、パスワードの定義名をDBUnitHelper.iniに定義した上で
     DBUnitHelperFactoryの引数に独自定義ファイル名を渡してください。

◯Helperの種類
  DBUnitHelperFactory.createSimpleHelper
   通常の単体試験
   setup実行時にテストデータを投入し、cleanUp実行時に投入したテストデータを削除します。

  DBUnitHelperFactory.createBackupHelper
   事前バックアップを実行
   setup実行時に引数で指定したテーブルの全レコードを保存したうえで削除し
   cleanUp実行時にバックアップしたレコードを元に戻します。

  DBUnitHelperFactory.createAdvanceCleanupHelper
   特定レコードの削除と追加を実行
   cleanUp実行時に引数で指定したレコードの削除と追加を行います。

