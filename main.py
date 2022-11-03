import subprocess
import coloredlogs
import os.path
from logging import info

coloredlogs.install(level='DEBUG')

brew_packages = [
    "ansible",
    "go",
    "coursier/formulas/coursier",
    "fish",
    "fisher",
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
    "rename",
    "tig",
    "tmux",
    "rustup-init",
    "nvm",
    "firebase-cli",
    "youtube-dl",
    "poetry",
    "yapf",
]

brew_casks = [
    "brave-browser",
    "cocoapods",
    "emacs",
    "hammerspoon",
    "telegram-desktop",
    "vlc",
    "alacritty",
    "docker",
    "font-cascadia-code",
    "font-cascadia-code-pl",
    "font-cascadia-mono",
    "font-cascadia-mono-pl",
]

repos = [
    ("git@github.com:Gonzih/dotfiles.git", "~/dotfiles"),
    ("git@github.com:Gonzih/.hammerspoon.git", "~/.hammerspoon"),
    ("https://github.com/syl20bnr/spacemacs", "~/.emacs.d"),
    ("git@github.com:Gonzih/.vim.git", "~/.vim"),
    ("git@github.com:Gonzih/.mc.git", "~/.config/mc"),
    ("git@github.com:Gonzih/.fish.git", "~/.config/fish"),
    ("git@github.com:Gonzih/.xmonad.git", "~/.xmonad"),
    ("git@github.com:Gonzih/.mutt.git", "~/.mutt"),
    ("git@github.com:Gonzih/ammonite-macos-helper.git", "~/mac-helper"),
    #("git@github.com:Gonzih/nix-home.git",     "~/.config/nixpkgs" ),
]


def run(cmd: str):
    info(cmd)
    subprocess.run(["bash", "-c", cmd])


def install_brew(pkg: str):
    run(f"arch -arm64 brew install {pkg}")


def install_cask(pkg: str):
    run(f"arch -arm64 brew install --cask {pkg}")


def install_repo(repo):
    src, path = repo
    fpath = os.path.expanduser(path)
    if not os.path.exists(fpath):
        run(f"git clone {src} {fpath}")


def main():
    run("brew tap homebrew/cask-fonts")
    list(map(install_brew, brew_packages))
    list(map(install_cask, brew_casks))
    list(map(install_repo, repos))


if __name__ == "__main__":
    main()
