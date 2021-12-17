# 2021 - 情報実験2

## すごろくゲーム：クライアントアプリケーション

.center {
display: block;
margin-left: auto;
margin-right: auto;
width: 50%;
}

<img src="https://github.com/SergeFan/sugoroku_client/blob/test/ui/src/main/resources/logo.png" class="center"/>

### プログラム構造

今回では、Java 9から導入したモジュールの概念を利用しました。現時点では、全体的にapp、netcomとui、3つのモジュールに分けました。後ほど、開発および機能からの観点で追加する可能性があります。

- app：appモジュールは基本的にプログラムの一番上層のモジュールです。他のモジュールを駆使してプログラム全体を動かします。
- netcom：netcomモジュールはインターネットコミュニケーションを管理するモジュールです。ネット通信に関する機能は全部このモジュールに実装します。
- ui：uiモジュールはグラフィックインターフェイスを管理するモジュールです。画面の表示や、ユーザーとのインタラクション機能はこのモジュールに実装します。

### 現時点の可能な問題点と対処方法

1. Windowsでの文字化け：システムロケールにUnicodeへの支援をオンにしてください。

2. Windowsでの画面崩れ：MainFrame.javaのsetMinimumSizeを1280*1024に変更してください。

### 現在工事中…
