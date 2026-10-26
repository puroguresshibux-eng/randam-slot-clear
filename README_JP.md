# Random Slot Clear（Fabric 1.21.1）

これはGitHub ActionsでMODの `.jar` を自動ビルドするためのプロジェクトです。PCへのIntelliJ IDEAのインストールは不要です。

## 手順
1. ZIPを展開する。
2. GitHubで新しい空のリポジトリを作成する（例: `random-slot-clear`）。
3. ZIP内のファイルをリポジトリにアップロードして、`main` ブランチにコミットする。`.github/workflows/build.yml` も必ず含める。
4. リポジトリの **Actions** タブを開き、`Build Fabric Mod` の実行が成功するまで待つ。
5. 成功した実行を開き、下の **Artifacts** から `RandomSlotClear-Fabric-1.21.1` をダウンロードする。
6. ArtifactのZIPを展開し、中の `.jar` をサーバーの `mods` フォルダへ入れる。

## コマンド
- `/randomclear` — 自分のインベントリのランダムな空でないスロットからアイテム1個を削除
- `/randomclear MikaelaVL` — MikaelaVLのインベントリからアイテム1個を削除

## 必要条件
- Minecraft Java 1.21.1
- Fabric Loader
- Fabric API（サーバーにも必要）
- Java 21
- コマンド実行者はOP権限レベル2以上

## 注意
このZIP自体は完成済み `.jar` ではなく、GitHubでビルドするためのソースプロジェクトです。
