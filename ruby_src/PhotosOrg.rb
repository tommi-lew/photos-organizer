require 'fileutils'

album_name = 'RUBY'
person_or_camera_model = 'Yukihiro'

def create_new_dir(date_modified, album_name, person_or_camera_model)
  day = "%02d" % [date_modified.day]

  new_dir_name = "#{date_modified.year}.#{date_modified.month}." +
    "#{day}-#{album_name}-[#{person_or_camera_model}]"

  begin
      Dir::mkdir('img/' + new_dir_name)
  rescue
    if not File.directory?('img/' + new_dir_name)
      puts "dir already exist!"
    end
  end

  new_dir_name
end

#get command line arguments
if ARGV.length < 0 || ARGV.length == 2
  album_name = ARGV[0]
  person_or_camera_model = ARGV[1]
else
  puts "Did not enter album name, default values will be used..."
end

photos_dir = Dir::open('img')
photos = photos_dir.entries

photos.delete_if {|x| x == "." || x == ".." }

photos.each do |ph|
  date_modified = File.mtime('img/' + ph)
  belongs_to_dir = create_new_dir(date_modified, album_name, person_or_camera_model)

  puts 'img/' + ph
  puts 'img/' + belongs_to_dir + '/'

  if not File.exists?('img/' + belongs_to_dir + '/' + ph)
    FileUtils::cp('img/' + ph, 'img/' + belongs_to_dir + '/')
  else
    puts "File #{ph} exist in directory, not copying..."
  end

end





