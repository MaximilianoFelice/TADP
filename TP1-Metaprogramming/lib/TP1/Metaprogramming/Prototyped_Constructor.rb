require "TP1/Prototyped"
require "TP1/Singleton_Module_Behaviour"

module TP

  module PrototypedConstructor

    attr_accessor :constructor, :prototype

    def initialize(instance, block = proc{})
      @prototype = instance
      @constructor = block
    end

    def new(*args)
      new_object = Object.new
      new_object.extend Prototyped

      new_object.set_prototype(@prototype)

      if (args[0].class == Hash) then
        self.build_with_hash(new_object, args[0])
      else
        build_with_block(new_object, *args)
      end
    end

    def build_with_block(new_object, *args)
      @constructor.call(new_object, *args)
      return new_object
    end

    def build_with_hash(new_object, hash)
      hash.each{ |key, value| new_object.instance_module_variable_set(key, value) }
      return new_object
    end
  end

end