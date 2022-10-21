import sys.process._
import java.io.File

val brewPackages = List(
  "ansible",
  "go",
  "coursier",
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
)

val brewCasks = List(
  "brave-browser",
  "cocoapods",
  "emacs",
  "hammerspoon",
  "telegram-desktop",
  "vlc",
  "font-cascadia-code",
  "font-cascadia-code-pl",
  "font-cascadia-mono",
  "font-cascadia-mono-pl",
)

val repos = List(
  ("git@github.com:Gonzih/.vim.git",     "~/.vim"            ),
  ("git@github.com:Gonzih/.mc.git",      "~/.config/mc"      ),
  ("git@github.com:Gonzih/.fish.git",    "~/.config/fish"    ),
  ("git@github.com:Gonzih/.xmonad.git",  "~/.xmonad"         ),
  ("git@github.com:Gonzih/.mutt.git",    "~/.mutt"           ),
  ("git@github.com:Gonzih/nix-home.git", "~/.config/nixpkgs" ),
)

def run(cmd: String): Boolean =
  s"echo $cmd".! == 0

def installBrew(pkg: String): Boolean =
  run(s"brew install $pkg")

def installCask(pkg: String): Boolean =
  run(s"brew install --cask $pkg")

def installRepo(repo: (String, String)): Boolean = {
  val src = repo._1
  val path = repo._2.replaceFirst("^~", System.getProperty("user.home"))
  new File(path).exists() || run(s"git clone $src $path")
}

@main
def main(): Unit =
  brewPackages.map(installBrew(_))
  brewCasks.map(installCask(_))
  repos.map(installRepo(_))
