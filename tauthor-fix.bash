git filter-branch --env-filter \
'if test "$GIT_AUTHOR_NAME" = "Titanor-Soulstone" ||
    test "$GIT_COMMITTER_NAME" = "Titanor-Soulstone"; then
    export GIT_AUTHOR_NAME="Titanor Soulstone"
    export GIT_COMMITTER_NAME="Titanor Soulstone"
fi' && rm -fr "$(git rev-parse --git-dir)/refs/original/"

