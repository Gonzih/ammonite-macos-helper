import sys.process._
import java.io.File

val brewPackages = List(
  "ansible",
  "go",
  "coursier/formulas/coursier",
  "fish",
  "htop",
  "mc",
  "ispell",
  "ntfs-3g",
  "scalastyle",
  "gnupg",
  "yubikey-personalization",
  "hopenpgp-tools",
  "ykman",
  "pinentry-mac",
  "wget",
  "tig",
)

val brewCasks = List(
  "brave-browser",
  "cocoapods",
  "emacs",
  "hammerspoon",
  "telegram-desktop",
  "vlc",
  "alacritty",
  "font-cascadia-code",
  "font-cascadia-code-pl",
  "font-cascadia-mono",
  "font-cascadia-mono-pl",
)

val repos = List(
  ("git@github.com:Gonzih/dotfiles.git",              "~/dotfiles"    ),
  ("git@github.com:Gonzih/.hammerspoon.git",          "~/.hammerspoon"),
  ("https://github.com/syl20bnr/spacemacs",           " ~/.emacs.d"   ),
  ("git@github.com:Gonzih/.vim.git",                  "~/.vim"        ),
  ("git@github.com:Gonzih/.mc.git",                   "~/.config/mc"  ),
  ("git@github.com:Gonzih/.fish.git",                 "~/.config/fish"),
  ("git@github.com:Gonzih/.xmonad.git",               "~/.xmonad"     ),
  ("git@github.com:Gonzih/.mutt.git",                 "~/.mutt"       ),
  ("git@github.com:Gonzih/ammonite-macos-helper.git", "~/mac-helper"  ),
  // ("git@github.com:Gonzih/nix-home.git",     "~/.config/nixpkgs" ),
)

def run(cmd: String): Boolean = {
  println(s"\n>>> $cmd")
  s"$cmd".! == 0
}

def installBrew(pkg: String): Boolean =
  run(s"arch -arm64 brew install $pkg")

def installCask(pkg: String): Boolean =
  run(s"arch -arm64 brew install --cask $pkg")

def installRepo(repo: (String, String)): Boolean = {
  val (src, path) = repo
  val fpath = path.replaceFirst("^~", System.getProperty("user.home"))
  new File(fpath).exists() || run(s"git clone $src $fpath")
}

@main def main(): Unit = {
  run("brew tap homebrew/cask-fonts")
  brewPackages.foreach(installBrew)
  brewCasks.foreach(installCask)
  repos.foreach(installRepo)
}
